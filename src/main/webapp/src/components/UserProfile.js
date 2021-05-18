import React, {useEffect, useState} from "react";
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {TokenService} from "../service/TokenService";
import {UserService} from "../service/UserService";
import {AuthenticationService} from "../service/clients/AuthenticationService";
import {useHistory} from "react-router-dom";

export default function UserProfile () {

    const divStyle = {height: 840, backgroundSize: 'cover', backgroundAttachment: 'fixed'};
    const [hasError, setError] = useState()
    const [disabled, setDisabled] = useState(true)
    const [open, setOpen] = React.useState(false);
    const [user, setUser] = useState({
        name:'',
        lastName: '',
        username: '',
        password: '',
    })

    useEffect(() => {
        fetchUser()
            .then(res => setUser(res.data))
            .catch(err => setError(err));
    },[])

    async function fetchUser() {
        const username = TokenService.decodeToken(TokenService.getToken()).sub
        return UserService.getUser(username)
    }

    function handleEnableClick() {
        setDisabled(!disabled)
        let submit = document.getElementById('submitBtn')
        let password = document.getElementById('passwordBtn')
        let edit = document.getElementById('editBtn')

        if (password.style.display === "initial" || password.style.display === "initial") {
            submit.style.display = "none";
            password.style.display = "none";
        } else {
            password.style.display = "initial";
            submit.style.display = "initial";
        }
    }

    function handleSubmit() {
        let name = document.getElementById('name').value
        let lastName = document.getElementById('lastName').value
        let username = document.getElementById('username').value

        if (validate(name, lastName, username)) {
            user.name = name
            user.lastName = lastName
            user.username = username
            UserService.editUser(user).then(() =>  alert("User details successfully updated, login again"))
            AuthenticationService.logout()
        }
        else
            alert("Please fill out all the fields")
    }

    function validate(name, lastName, username) {
        console.log(name, lastName, username)
        if (name !== "" && lastName !== ""  && username !== "") {
            return true
        }
        return false
    }

    function validatePassword(oldPassword, newPassword, newPasswordConfirm) {
        if (oldPassword !== "" && newPassword !=="" && newPasswordConfirm !=="") {
            return true
        }
        return false
    }

    const handleClickOpen = () => {setOpen(true);};
    const handleClose = () => {setOpen(false);};

    const handlePassword = () => {
        let oldPassword = document.getElementById('oldPassword').value
        let newPassword = document.getElementById('newPassword').value
        let newPasswordConfirm = document.getElementById('newPasswordConfirm').value
        if (validatePassword(oldPassword, newPassword, newPasswordConfirm)) {
            const userPasswordChange = {
                'username': user.username,
                'oldPassword': oldPassword.trim(),
                'password': newPassword.trim(),
                'passwordConfirm': newPasswordConfirm.trim(),
            }
            console.log(userPasswordChange)
            UserService.changePassword(userPasswordChange)
            setOpen(false)
        }
        else
            alert("Fill out both fields !")
    };

    const changeInputHandler = (event, prop) => {
        const usr = {...user};
        usr[prop] = event.target.value;
        setUser(usr)
    }

    console.log(user)

    return(
        <div style={divStyle}>
            <div className="form-size">
                <h1>PROFILE</h1>

                <TextField className="input-margin" label="Name" id="name"
                           type="text" variant="outlined"  disabled = {(disabled)? "disabled" : ""}
                           value={user.name} onChange={(event) => changeInputHandler(event, 'name')}/>
                <TextField className="input-margin" label="Last name" id="lastName"
                           type="text" variant="outlined"  disabled = {(disabled)? "disabled" : ""}
                           value={user.lastName} onChange={(event) => changeInputHandler(event, 'lastName')}/>
                <TextField className="input-margin" label="Username" id="username"
                           type="text" variant="outlined"  disabled = {(disabled)? "disabled" : ""}
                           value={user.username} onChange={(event) => changeInputHandler(event, 'username')}/>
                <hr />
                <Button size="large" id="editBtn" color="inherit" onClick={handleEnableClick}>
                    Edit
                </Button>
                <br />
                <Button size="large" id="passwordBtn" color="inherit" onClick={handleClickOpen} style={{display: 'none'}} >
                    Change password
                </Button>
                <br />
                <Button size="large" id="submitBtn" color="inherit" onClick={() => handleSubmit()} style={{display: 'none'}} >
                    Submit
                </Button>
            </div>
            <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="oldPassword"
                        label="Old password"
                        type="password"
                    />
                    <br />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="newPassword"
                        label="New password"
                        type="password"
                    />
                    <br />
                    <TextField
                        autoFocus
                        margin="dense"
                        id="newPasswordConfirm"
                        label="New password confirm"
                        type="password"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handlePassword} color="primary">
                        Continue
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}
