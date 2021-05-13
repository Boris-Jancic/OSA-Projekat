// import React, {Component} from "react";
//
// class AddArticle extends Component {
//
//     state = {
//         profileImg: "data:image/png;base64,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",
//         selectedImg: null
//     }
//
//     fileSelectedHandler = event =>  {
//         console.log(event.target.files[0])
//         const reader = new FileReader()
//         reader.onload = () => {
//             if (reader.readyState === 2) {
//                 this.setState({profileImg: reader.result})
//                 this.setState({selectedImg: event.target.files[0]})
//             }
//         }
//
//         reader.readAsDataURL(event.target.files[0])
//     }
//
//     addArticle = (e) => {
//         const name = document.getElementById("name").value
//         const description = document.getElementById("description").value
//         const price = document.getElementById("price").value
//         if (name !== "" && description !== "" && price !== "" && this.state.selectedImg !== null) {
//             e.preventDefault();
//             const formData = new FormData();
//             formData.append('imageFile', this.state.selectedImg);
//             formData.append("name", document.getElementById("name").value)
//             formData.append("description", document.getElementById("description").value)
//             formData.append("price", document.getElementById("price").value)
//             formData.append("imageName", this.state.selectedImg.name)
//
//             fetch('http://localhost:8080/addArticle', {
//                 mode: 'no-cors',
//                 method: 'post',
//                 body: formData
//             }).then(res => {
//                 console.log(res.data);
//                 alert("Article added successfully.")
//             });
//         } else {
//             alert("Make sure to fill out all the fields !")
//         }
//     };
//
//     render() {
//         return(
//             <>
//                 <form className="form-size" method="POST" action="login">
//                     <h1>Add article</h1>
//
//                     <input className="form-control input-margin" id="name" type="text" placeholder="Name"/>
//                     <input className="form-control input-margin" id="description" type="text" placeholder="Description"/>
//                     <input className="form-control input-margin" id="price" type="number" placeholder="Price"/>
//                     <input accept="image/*" className="form-control input-margin" id="picture" type="file" onChange={this.fileSelectedHandler}/>
//                     <img className="img-margin" src={this.state.profileImg} id="img"  alt=""/>
//                     <input className="form-control btn input-margin" onClick={this.addArticle} value="Submit" />
//                 </form>
//             </>
//         )
//     }
// }
//
// export default AddArticle

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
                history.push("./browse")
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