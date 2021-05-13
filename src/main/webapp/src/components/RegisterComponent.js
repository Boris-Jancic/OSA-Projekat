import React, { Component } from "react";
import {Redirect, useHistory} from "react-router-dom";

class Register extends Component {
    constructor() {
        super();
        this.state = {
            user: "seller",
            submitValid: false
        };
        this.onChangeValue = this.onChangeValue.bind(this);
    }

    onChangeValue(event) {
        let email = document.getElementById('sellerName')
        let name = document.getElementById('sellerEmail')
        if (event.target.id === 'seller') {
            email.style.visibility = "visible";
            name.style.visibility = "visible";
            this.state.user = 'seller';
        }
        else {
            email.style.visibility = "hidden";
            name.style.visibility = "hidden";
            this.state.user = 'buyer';
        }
        console.log(this.state.user)
    }

    registerUser = () => {
        let name = document.getElementById('name').value;
        let lastName = document.getElementById('lastName').value;
        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;
        let address = document.getElementById('address').value;

        if (this.state.user === 'buyer'){
            if (this.validateRegister(name, lastName, username, password, address, '', '')) {
                let buyer = {
                    "username": username,
                    "blocked": false,
                    "lastName": lastName,
                    "name": name,
                    "password": password,
                    "address": address
                };
                fetch('http://localhost:8080/buyerRegistration', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(buyer),
                })
                    .then(response => response.json())
                    .then(buyer => {
                        console.log('Success:', buyer);
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                });
            }
        } else if (this.state.user === 'seller') {
            let sellerName = document.getElementById('sellerName').value;
            let email = document.getElementById('sellerEmail').value;

            if ( this.validateRegister(name, lastName, username, password, address, email, sellerName)) {
                let seller = {
                    "username":username,
                    "blocked": false,
                    "lastName":lastName,
                    "name":name,
                    "password":password,
                    "address":address,
                    "email":email,
                    "sellerName":sellerName
                };
                fetch('http://localhost:8080/sellerRegistration', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(seller),
                })
                    .then(response => response.json())
                    .then(buyer => {
                        console.log('Success:', buyer);
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            }
        }
    }

    validateRegister = (name, lastName, username, password, address, email, sellerName) => {
        let valid = true;
        if (name === "" || lastName === ""  || username === "" || password === "" || address === "") {
            valid = false
        }
        if (this.state.user === 'seller') {
            if (email === '' || sellerName === '')
                valid = false
        }

        if (!valid) {
            this.state.submitValid = false
            alert("Please fill out all the required fields")
        } else {
            this.state.submitValid = true
            alert("Succesfully registered !")
        }

        return valid
    }

    render() {

        return (
            <>
                <form className="form-size" method="POST" action="login">
                    <h1>Register</h1>

                    <input type="button" className="btn input-margin" value="As a seller" id="seller" onClick={this.onChangeValue} />
                    <input type="button" className="btn input-margin" value="As a buyer" id="buyer" onClick={this.onChangeValue} />

                    <input className="form-control input-margin" id="name" type="text" placeholder="Name"/>
                    <input className="form-control input-margin" id="lastName" type="text" placeholder="Last name"/>
                    <input className="form-control input-margin" id="username" type="text" placeholder="Address"/>
                    <input className="form-control input-margin" id="password" type="text" placeholder="Username"/>
                    <input className="form-control input-margin" id="address" type="password" placeholder="Password"/>

                    <input className="form-control input-margin" type="text" id="sellerName" placeholder="Seller name"/>
                    <input className="form-control input-margin" type="email" id="sellerEmail" placeholder="Email"/>

                    <input className="form-control btn input-margin" value="Submit" onClick={this.registerUser} />
                </form>
            </>
        );
    }
}

export default Register;