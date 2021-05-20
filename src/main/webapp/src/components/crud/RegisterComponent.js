import React, {Component, useState} from "react";
import {Redirect, useHistory} from "react-router-dom";
import {TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";

export default function Register () {

    const [user, setUser] = useState('seller')
    const [submitValid, setSubmitValid] = useState(false)
    const history = useHistory();

    const divStyle = {height: 930, backgroundSize: 'cover'};

    function onChangeValue(type) {
        let email = document.getElementById('sellerName')
        let name = document.getElementById('sellerEmail')
        if (type === 'seller') {
            email.removeAttribute('hidden');
            name.removeAttribute('hidden');
            setUser('seller')
        }
        else {
            email.hidden = true;
            name.hidden = true;
            setUser('buyer')

        }
        console.log(user)
    }

    function registerUser () {
        let name = document.getElementById('name').value;
        let lastName = document.getElementById('lastName').value;
        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;
        let address = document.getElementById('address').value;

        if (user === 'buyer'){
            if (validateRegister(name, lastName, username, password, address, '', '')) {
                let buyer = {
                    "username": username,
                    "lastName": lastName,
                    "name": name,
                    "password": password,
                    "address": address,
                };
                console.log(buyer)
                fetch('http://localhost:8080/buyer/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(buyer),
                })
                    .then(response => response.json())
                    .then(history.push('/login'))
                    .catch((error) => {
                        console.error('Error:', error);
                });
            }
        } else if (user === 'seller') {
            let sellerName = document.getElementById('sellerName').value;
            let email = document.getElementById('sellerEmail').value;

            if (validateRegister(name, lastName, username, password, address, email, sellerName)) {
                let seller = {
                    "username":username,
                    "blocked": false,
                    "lastName":lastName,
                    "name":name,
                    "password":password,
                    "address":address,
                    "email":email,
                    "sellerName":sellerName,
                };
                console.log(seller)
                fetch('http://localhost:8080/seller/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(seller),
                })
                    .then(response => response.json())
                    .then(history.push('/login'))
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            }
        }
    }

    function validateRegister (name, lastName, username, password, address, email, sellerName) {
        let valid = true;
        if (name === "" || lastName === ""  || username === "" || password === "" || address === "") {
            valid = false
        }
        if (user === 'seller') {
            if (email === '' || sellerName === '')
            valid = false
        }

        if (!valid) {
            setSubmitValid(false)
            alert("Please fill out all the required fields")
        } else {
            setSubmitValid(true)
        }

        return valid
    }

    return (
        <div style={divStyle} >
            <div className="form-size">
                <h1>Register as a</h1>

                <span>
                    <Button size="large" id="buyer" color="inherit" onClick={() => onChangeValue('buyer')}>
                        buyer
                    </Button>
                    <Button size="large" id="seller" color="inherit" onClick={() => onChangeValue('seller')}>
                        seller
                    </Button>
                </span>
                <hr />

                <TextField className="input-margin" label="Name" id="name" type="text" variant="outlined" />
                <TextField className="input-margin" label="Last name" id="lastName" type="text" variant="outlined" />
                <TextField className="input-margin" label="Username" id="username" type="text" variant="outlined" />
                <TextField className="input-margin" label="Password" id="password" type="password" variant="outlined" />
                <TextField className="input-margin" label="Address" id="address" type="text" variant="outlined" />
                <TextField className="input-margin" placeholder="Seller name" id="sellerName" type="text" variant="outlined"/>
                <TextField className="input-margin" placeholder="Email" id="sellerEmail" type="email" variant="outlined"/>

               <hr />
                <Button size="large" color="inherit" onClick={() => registerUser()}>
                    Submit
                </Button>
            </div>
        </div>
    );
}