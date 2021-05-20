import React, {useEffect, useState} from "react";
import {useHistory, useParams} from "react-router-dom";
import {UserService} from "../service/UserService";
import {AuthenticationService} from "../service/clients/AuthenticationService";
import {Card, CardActions, CardContent, CardMedia, Grid, Typography} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {classes} from "istanbul-lib-coverage";

export default function SellerLayout() {
    const history = useHistory();
    const [sellers, setSellers] = useState([])
    const [hasError, setError] = useState()

    useEffect(() => {
        fetchSellers()
            .then(res => setSellers(res.data))
            .catch(err => setError(err));
    },[])

    const {id} = useParams();

    const fetchSellers = async () => {
        try {
            return await UserService.getSellers()
        } catch (error) {
            console.error(`Error while fetching articles: ${error}`);
        }
    }
    console.log(sellers)

    return(<div className={classes.root} className="card-view">
            <Grid
                container
                spacing={3}
                direction="row"
                justify="flex-start"
                alignItems="flex-start"
            >
                {sellers.map(elem => (
                    <Grid item xs={12} sm={6} md={6} key={sellers.indexOf(elem)}>
                        <Card>
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="h1">
                                    {elem.sellerName}
                                </Typography>
                                <hr />
                                <Typography variant="body2" color="textSecondary" component="h2">
                                    <b> {elem.address} </b>
                                    <hr />
                                    <b> {elem.email} </b>
                                </Typography>
                                <hr />
                                <Typography variant="body2" color="textSecondary" component="p">
                                   Selling since : {elem.sellingSince}
                                </Typography>
                            </CardContent>

                            <CardActions style={{
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                            }}><Button size="small" color="primary" href={"/browse/" + elem.user.id}>
                                    articles
                                </Button>
                                <Button size="small" color="primary" href={"/seller/" + elem.user.username}>
                                    comments
                                </Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </div>
    );
}