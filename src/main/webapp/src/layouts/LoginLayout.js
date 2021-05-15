import React, {useState} from 'react';
import {TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {AuthenticationService} from "../service/clients/AuthenticationService";

const LoginLayout = () => {

    const [credentials, setCredentials] = useState({
        username: "",
        password: "",
    });

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;

        // ... - Destructuring assignment - omogućuje raspakivanje vrednosti objekata ili nizova
        // setCredentails će zameniti stanje novim objektom koji uzima vrednosti iz prethodnog stanja kredencijala
        // ali sa ažuriranom vrednošću [name] polja
        setCredentials({ ...credentials, [name]: val });
    };

    const login = async () => {
        await AuthenticationService.login(credentials);
    };

    return (
        <div className="form-size">
            <h1>Login</h1>
            <hr />
            <TextField label="Username"
                       id="username"
                       type="text"
                       value={credentials.username}
                       onChange={handleFormInputChange("username")}/>
            <hr />
            <TextField label="Password"
                       id="description"
                       type="password"
                       value={credentials.password}
                       onChange={handleFormInputChange("password")}/>
            <hr />

            <Button size="large" color="inherit" onClick={login}>
                Submit
            </Button>
        </div>
    );
}
export default LoginLayout