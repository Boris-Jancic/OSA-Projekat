import {useState} from "react";
import {useHistory} from "react-router-dom";
import {ArticleService} from "../../service/ArticleService";
import {UserService} from "../../service/UserService";
import {AuthenticationService} from "../../service/clients/AuthenticationService";
import {TokenService} from "../../service/TokenService";
import {number} from "prop-types";

export default function AddArticle() {
    const [state, setState] = useState({
        selectedImg: null,
        selectedImgName: ''
    })
    const divStyle = {height: 930, backgroundSize: 'cover'};
    const history = useHistory();

    function fileSelectedHandler(event) {
        console.log(event.target.files[0])
        const reader = new FileReader()
        reader.onload = () => {
            if (reader.readyState === 2) {
                state.selectedImg = event.target.files[0]
                state.selectedImgName = event.target.files[0].name
            }
        }
        reader.readAsDataURL(event.target.files[0])
    }

    async function addArticle(e) {
        const name = document.getElementById("name").value
        const description = document.getElementById("description").value
        const price = document.getElementById("price").value
        if (name !== "" && description !== "" && price !== "" && state.selectedImg !== null) {

            try {
                e.preventDefault();

                console.log(state.selectedImg)
                const formData = new FormData()
                const username = TokenService.decodeToken(TokenService.getToken()).sub
                const seller = (await UserService.getUser(username)).data
                console.log(username)
                console.log(seller)
                formData.append('imageFile', state.selectedImg);
                formData.append("name", name)
                formData.append("description", description)
                formData.append("price", price)
                formData.append("sellerId", seller.id)

                await ArticleService.addArticle(formData)
                alert("Article added successfully.")
                history.push("./browse")
            } catch (error) {
                console.error(`Error while adding new article: ${error}`);
            }
        } else {
            alert("Make sure to fill out all the fields !")
        }
    }
    return(
        <div style={divStyle}>
            <form className="form-size" method="POST">
                <h1>Add article</h1>

                <input className="form-control input-margin" id="name" type="text" placeholder="Name"/>
                <input className="form-control input-margin" id="description" type="text" placeholder="Description"/>
                <input className="form-control input-margin" id="price" type="number" placeholder="Price"/>
                <input accept="image/*" className="form-control input-margin" id="picture" type="file" onChange={fileSelectedHandler}/>
                <input className="form-control btn input-margin" onClick={addArticle} value="Submit" />
            </form>
        </div>
    )
}