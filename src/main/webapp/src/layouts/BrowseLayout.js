import React, {useEffect, useState} from 'react'
import { makeStyles } from '@material-ui/core/styles'
import {Grid, Card, CardContent, Typography, CardHeader} from '@material-ui/core/'
import Button from "@material-ui/core/Button";
import {CardActions, CardMedia} from "@material-ui/core";
import {AuthenticationService} from "../service/clients/AuthenticationService";
import {ArticleService} from "../service/ArticleService";
import stockPhoto from '../static/images/stockBike.jpg'

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
        maxWidth: 400,
        padding: theme.spacing(2)
    },
    media: {
        height: 150,
    },
}))

class ImageBackground extends React.Component<{ style: Property.BackgroundImage | string }> {
    render() {
        return null;
    }
}

export default function BrowseLayout() {
    const classes = useStyles()
    const [articles, setArticles] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchArticles()
    },[])

    const fetchArticles = async () => {
        try {
            const response = await ArticleService.getArticles()
            setArticles(response.data);
        } catch (error) {
            console.error(`Error while fetching articles: ${error}`);
        }
    }

    const handleDelete = async (id) => {
        await ArticleService.deleteArticle(id);
        setArticles((articles) => articles.filter((article) => article.id !== id));
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
                        <Grid item xs={12} sm={6} md={3} key={articles.indexOf(elem)}>
                                <Card>
                                    <CardMedia
                                        className={classes.media}
                                        image={stockPhoto}
                                        title="Contemplative Reptile"
                                    />
                                    <CardContent>
                                        <Typography gutterBottom variant="h5" component="h1">
                                            {elem.name}
                                        </Typography>
                                        <Typography variant="body2" color="textSecondary" component="h2">
                                            <b> {elem.price} € </b>
                                        </Typography>
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
