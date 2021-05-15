import React, {useEffect, useState} from "react";
import {useParams, useHistory } from "react-router-dom";
import {CardActions, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";

export default function EditArticle () {

    const [article, setArticle] = useState({
        id: '',
        name:'',
        description: '',
        price: '',
        imageName: ''
    })
    const [hasError, setError] = useState()
    const history = useHistory();

    const {id} = useParams();

    async function fetchArticle() {
        const res = await fetch('http://localhost:8080/getArticle/' + id);
        return res.json()
    }

    useEffect(() => {
        fetchArticle()
            .then(res => setArticle(res))
            .catch(err => setError(err));
    },[])

    function editArticle() {
        const name = document.getElementById("name").value
        const description = document.getElementById("description").value
        const price = document.getElementById("price").value

        if (name !== "")
            article.name = name
        if (description !== "")
            article.description = description
        if (price !== "")
            article.price = Number(price)

        console.log(article)
        fetch('http://localhost:8080/updateArticle', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(article),
        })
        history.push("../browse")
        alert("Article updated successfully.")

    }

    return(
        <>
            <div className="form-size">
                <h1>Edit article {article.name}</h1>
                <hr />
                <TextField label="Name" id="name" type="text" placeholder={article.name}/>
                <hr />
                <TextField label="Description" id="description" type="text" placeholder={article.description}/>
                <hr />
                <TextField label="Price" id="price" type="number" placeholder={article.price}/>
                <hr />

                <Button size="large" color="inherit" onClick={editArticle}>
                    Submit
                </Button>
            </div>
        </>
    )
}