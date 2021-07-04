import React, {useEffect, useState} from "react";
import {useHistory, useParams} from "react-router-dom";
import {UserService} from "../service/UserService";
import {Card, CardActions, CardContent, Grid, Typography} from "@material-ui/core";
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
    }, [])

    const {id} = useParams();

    const fetchSellers = async () => {
        try {
            const sellerData = await UserService.getSellers()
            let sellers = sellerData.data
            for (const seller of sellers) {
                const grade = await UserService.getSellerGrade(seller.user.username)
                if (grade.data === '')
                    seller['grade'] = "This seller has not been rated yet"
                else
                    seller['grade'] = grade.data
            }
            setSellers(sellerData.data)
        } catch (error) {
            console.error(`Error while fetching articles: ${error}`);
        }
    }
    console.log(sellers)

    function handleComments(username, grade) {
        localStorage.setItem("GRADE", grade)
        window.location.assign("seller/" + username)
    }

    function handleArticles(id) {
        localStorage.removeItem("cartItems")
        window.location.assign("browse/" + id)
    }

    return (<div className={classes.root} className="card-view">
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
                                <hr/>
                                <Typography variant="body2" color="textSecondary" component="h2">
                                    <b> {elem.address} </b>
                                    <hr/>
                                    <b> {elem.email} </b>
                                    <hr/>
                                    <b> Grade: <u><b> {elem.grade} </b></u> </b>
                                </Typography>
                                <hr/>
                                <Typography variant="body2" color="textSecondary" component="p">
                                    Selling since : {elem.sellingSince}
                                </Typography>
                            </CardContent>

                            <CardActions style={{
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                            }}><Button size="small" color="primary" onClick={() => handleArticles(elem.user.id)}>
                                articles
                            </Button>
                                <Button size="small" color="primary"
                                        onClick={() => handleComments(elem.user.username, elem.grade)}>
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