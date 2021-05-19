
import React, {Component, useEffect, useState} from 'react';
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {classes} from "istanbul-lib-coverage";
import {UserService} from "../../service/UserService";
import {ArticleService} from "../../service/ArticleService";

class OrderTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: []
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/users')
            .then((response) => response.json())
            .then(userData => {
                this.setState({ users: userData });
            });
    }

    handleAction(user) {
        if (user.blocked === false)
            user.blocked = true
        else
            user.blocked = false
        UserService.editUser(user)
        window.location.reload()
    }

    render() {
        return(
            <div className="form-table">
                <TableContainer component={Paper}>
                    <Table className={classes.table} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align={"center"}>ID</TableCell>
                                <TableCell align={"center"}>Name</TableCell>
                                <TableCell align={"center"}>Last name</TableCell>
                                <TableCell align={"center"}>Username</TableCell>
                                <TableCell align={"center"}>Actions</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>

                            {this.state.users.map(row => (
                                <TableRow key={row.name}>
                                    <TableCell align={"center"}>{row.id}</TableCell>
                                    <TableCell align={"center"}>{row.name}</TableCell>
                                    <TableCell align={"center"}>{row.lastName}</TableCell>
                                    <TableCell align={"center"}>{row.username}</TableCell>
                                    <TableCell align={"center"}>

                                        {(row.blocked === false) ? (
                                            <Button variant="contained" color="primary" onClick={() => this.handleAction(row)}>
                                                Block
                                            </Button>
                                        ) : (
                                            <Button variant="contained" color="primary" onClick={() => this.handleAction(row)}>
                                                Unblock
                                            </Button>
                                        )}
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        );
    }
}

export default UserTable