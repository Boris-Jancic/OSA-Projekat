import {useState} from "react";
import {useHistory} from "react-router-dom";

export default function AddArticle() {
    const [state, setState] = useState({
        selectedImg: null,
        selectedImgName: ''
    })
    const history = useHistory();

    function fileSelectedHandler(event) {
        console.log(event.target.files[0])
        const reader = new FileReader()
        reader.onload = () => {
            if (reader.readyState === 2) {
                setState({selectedImg: event.target.files[0]})
                state.selectedImgName = event.target.files[0].name
            }
        }
        reader.readAsDataURL(event.target.files[0])
    }

    function addArticle(e) {
        const name = document.getElementById("name").value
        const description = document.getElementById("description").value
        const price = document.getElementById("price").value
        if (name !== "" && description !== "" && price !== "" && state.selectedImg !== null) {
            e.preventDefault();
            const formData = new FormData();
            console.log(state.selectedImg)
            formData.append('imageFile', state.selectedImg);
            formData.append("name", document.getElementById("name").value)
            formData.append("description", document.getElementById("description").value)
            formData.append("price", document.getElementById("price").value)
            formData.append("imageName", state.selectedImgName)

            fetch('http://localhost:8080/addArticle', {
                mode: 'no-cors',
                method: 'post',
                body: formData
            }).then(res => {
                console.log(res.data);
                alert("Article added successfully.")
                // history.push("./browse")
            });
        } else {
            alert("Make sure to fill out all the fields !")
        }
    }
    return(
        <>
            <form className="form-size" method="POST">
                <h1>Add article</h1>

                <input className="form-control input-margin" id="name" type="text" placeholder="Name"/>
                <input className="form-control input-margin" id="description" type="text" placeholder="Description"/>
                <input className="form-control input-margin" id="price" type="number" placeholder="Price"/>
                <input accept="image/*" className="form-control input-margin" id="picture" type="file" onChange={fileSelectedHandler}/>
                <input className="form-control btn input-margin" onClick={addArticle} value="Submit" />
            </form>
        </>
    )
}