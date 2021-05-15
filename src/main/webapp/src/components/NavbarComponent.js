import React from 'react';
import {Navbar, Nav} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import {TokenService} from "../service/TokenService";
import {AuthenticationService} from "../service/clients/AuthenticationService";
import {createMuiTheme, Link, MuiThemeProvider} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {lightBlue} from "@material-ui/core/colors";

const NavbarComponent = () => {

    return (
        <>
            <Navbar collapseOnSelect expand="lg" bg="success" variant="light">
            <Navbar.Brand href="home">Bamboo</Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="mr-auto">
                        <Button href="/browse">Browse</Button>
                        {AuthenticationService.getRole() === "ROLE_SELLER" && (
                            <Button href="/addArticle">Add article</Button>
                        )}
                    </Nav>
                    <Nav>
                        {TokenService.getToken() ? (
                            <Button onClick={() => AuthenticationService.logout()}>Log out</Button>
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
