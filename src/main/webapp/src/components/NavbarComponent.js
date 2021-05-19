import React, {useEffect, useState} from 'react';
import {Navbar, Nav} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import {TokenService} from "../service/TokenService";
import {AuthenticationService} from "../service/clients/AuthenticationService";
import {createMuiTheme, Link, MuiThemeProvider} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {lightBlue} from "@material-ui/core/colors";
import logo from '../static/images/logo.svg'
import {UserService} from "../service/UserService";
import AxiosClient from "../service/clients/AxiosClient";

function NavbarComponent () {
    const [browseUrl, setBrowseUrl] = useState('')
    const [hasError, setError] = useState()


    useEffect(() => {
        fetchUser()
            .then(res => setBrowseUrl("/browse/" + res.data.id))
            .catch(err => setError(err));
    },[])

    async function fetchUser() {
        const username = TokenService.decodeToken(TokenService.getToken()).sub
        return  UserService.getUser(username)
    }

    return (
        <>
            <Navbar collapseOnSelect expand="lg" bg="success" variant="light">
                <Navbar.Brand className="navbar-brand mb-0 h1" href="home" expand="lg">
                    <img
                        src={logo}
                        width="30"
                        height="30"
                        className="d-inline-block align-top"
                    />
                    Bamboo
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                        {AuthenticationService.getRole() === "ROLE_BUYER" && (
                            <Button href="/sellers">Sellers</Button>
                        )}
                        {AuthenticationService.getRole() === "ROLE_SELLER" && (
                            <Button href={browseUrl}>My articles</Button>
                            )}
                        {AuthenticationService.getRole() === "ROLE_SELLER" && (
                            <Button href="/addArticle">Add article</Button>
                        )}
                        {AuthenticationService.getRole() === "ROLE_ADMIN" && (
                            <Button href="/users">User managment</Button>
                        )}
                    </Nav>
                    <Nav>
                        {TokenService.getToken() ? (
                            <>
                                <Button href="/profile">Profile</Button>
                                <Button onClick={() => AuthenticationService.logout()}>Log out</Button>
                            </>
                        ) : (
                            <>
                                <Button href="/register">Register</Button>
                                <Button href="/login">Log in</Button>
                            </>
                        )}
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        </>
    );
};

export default NavbarComponent;
