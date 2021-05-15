import React, {useEffect, useState} from 'react'
import { makeStyles } from '@material-ui/core/styles'
import {Grid, Card, CardContent, Typography, CardHeader} from '@material-ui/core/'
import Button from "@material-ui/core/Button";
import {CardActions, CardMedia} from "@material-ui/core";
import {AuthenticationService} from "../service/clients/AuthenticationService";

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
        padding: theme.spacing(2)
    }
}))

export default function BrowseLayout() {
    const classes = useStyles()
    const [articles, setArticles] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .then(res => setArticles(res))
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        const res = await fetch('http://localhost:8080/allArticles', [])
        return res.json()
    }

    async function handleDelete(id) {
        await fetch('http://localhost:8080/deleteArticle/' + id,{
            method: 'delete',
            body: id
        }).then(res => {
            console.log(res.data);
            alert("Article deleted successfully.")
            window.location.reload(false);
        });
    }

    console.log(articles)

    return (
        <div className={classes.root} class="card-view">
            <Grid
                container
                spacing={3}
                direction="row"
                justify="flex-start"
                alignItems="flex-start"
            >
                {articles.map(elem => (
                    <Grid item xs={12} sm={6} md={4} key={articles.indexOf(elem)}>
                            <Card>
                                <CardMedia
                                    image= "./static/images/stockBike.jpg"
                                    className={classes.media}
                                />
                                <CardHeader
                                    title={`${elem.name}`}
                                    subheader={`${elem.price} €`}
                                />
                                <CardContent>
                                    <Typography variant="body2" color="textSecondary" component="p">
                                        {elem.description}
                                    </Typography>
                                </CardContent>
                                {AuthenticationService.getRole() === "ROLE_SELLER" && (
                                    <CardActions>
                                    <Button size="small" color="primary" href={"/editArticle/" + elem.id}>
                                        Edit
                                    </Button>
                                    <Button size="small" color="primary" onClick={() => handleDelete(elem.id)}>
                                        Delete
                                    </Button>
                                </CardActions>
                                )}
                            </Card>
                        </Grid>

                ))}
            </Grid>
        </div>
    )
}
