
import React, {Component, useEffect, useState} from 'react';
import {
    Checkbox,
    Dialog, DialogActions,
    DialogContent, DialogTitle, FormControlLabel,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow, TextField
} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {classes} from "istanbul-lib-coverage";
import {UserService} from "../../service/UserService";
import {ArticleService} from "../../service/ArticleService";
import {OrderService} from "../../service/OrderService";
import {TokenService} from "../../service/TokenService";
import {CheckBox} from "@material-ui/icons";
import Switch from "react-bootstrap/Switch";
import {number} from "prop-types";

class OrderTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            order: {
                id: "",
                anonymousComment: false,
                archivedComment: false,
                comment: "awaiting grade",
                delivered: false,
                grade: 0,
                hourlyRate: "",
                user: "tada" ,
            },
            open: false,
            fullWidth: true,
            anonymous: false
        };
    }

    handleFullWidthChange = (event) => {
        this.setState({fullWidth: event.target.checked})
    };

    componentDidMount() {
        const username = TokenService.decodeToken(TokenService.getToken()).sub
        OrderService.getOrders(username)
            .then((response) => response.data)
            .then(orderData => {
                this.setState({ orders: orderData });
            });
    }

    handleClickOpen = (order) => {
        this.setState({open: true})
        this.setState({order: order})
    };

    handleClose = () => {this.setState({open: false})};

    handleAction = async () => {
        let comment = document.getElementById('comment').value
        let grade = document.getElementById('grade').value

        this.state.order.delivered = true
        this.state.order.comment = comment
        this.state.order.grade = grade
        this.state.order.anonymousComment = this.state.anonymous

        this.setState({open: false})
        for (let i=0; i < this.state.orders.length; i++) {
            if (this.state.orders[i].delivered === true) {
                this.state.orders.splice(i, 1)
            }
        }
        await OrderService.putOrder(this.state.order)
    }

    handleCheckBoxChange = (e) => {
        this.setState({anonymous: e.target.checked})
    }

    render() {
        console.log(this.state.orders)
        return(
            <div className="form-table">
                <TableContainer component={Paper}>
                    <Table className={classes.table} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align={"center"}>ID</TableCell>
                                <TableCell align={"center"}>Date</TableCell>
                                <TableCell align={"center"}>Actions</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>

                            {this.state.orders.map(row => (
                                <TableRow key={row.name}>
                                    <TableCell align={"center"}>{row.id}</TableCell>
                                    <TableCell align={"center"}>{row.hourlyRate}</TableCell>
                                    <TableCell align={"center"}>

                                    <Button variant="contained" color="primary" onClick={() => this.handleClickOpen(row)}>
                                        Set delivered status
                                    </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>

                <Dialog open={this.state.open}
                        fullWidth={this.state.fullWidth} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Order review</DialogTitle>
                    <DialogContent>
                        <TextField
                            fullWidth={this.state.fullWidth}
                            autoFocus
                            margin="dense"
                            id="comment"
                            label="Comment"
                            type="text"
                        />
                        <br />
                        <TextField
                            fullWidth={this.state.fullWidth}
                            autoFocus
                            margin="dense"
                            id="grade"
                            label="Grade"
                            type="number"
                            min={1}
                            max={5}
                        />
                        <br />
                        <FormControlLabel
                            value="end"
                            id="anonymous"
                            control={<Checkbox color="success" />}
                            onChange={this.handleCheckBoxChange}
                            label="Anonymous comment"
                            labelPlacement="end"
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={() => this.handleClose()} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={() => this.handleAction()} color="primary">
                            Continue
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}

export default OrderTable