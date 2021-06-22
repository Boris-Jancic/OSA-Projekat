import React, {useEffect, useState} from 'react'
import { makeStyles } from '@material-ui/core/styles'
import {Grid, Card, CardContent, Typography, CardHeader} from '@material-ui/core/'
import Button from "@material-ui/core/Button";
import {CardActions, CardMedia, Dialog, DialogActions, DialogContent, TextField} from "@material-ui/core";
import {AuthenticationService} from "../service/clients/AuthenticationService";
import {ArticleService} from "../service/ArticleService";
import stockPhoto from '../static/images/stockBike.jpg'
import {TokenService} from "../service/TokenService";
import {UserService} from "../service/UserService";
import {useParams} from "react-router-dom";
import {Alert, Form} from "react-bootstrap";
import {DiscountService} from "../service/DiscountService";

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
        maxWidth: 300,
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
    const [seller, setSeller] = useState({})
    const [article, setArticle] = useState({})
    const [articles, setArticles] = useState([])
    const [discountsA, setDiscountsA] = useState([])
    const [discounts, setDiscounts] = useState([])
    const [cartArticles, setCartArticles] = useState([])
    const [hasError, setError] = useState(false)
    const [open, setOpen] = React.useState(false);

    useEffect(() => {
        // fetchSeller().then(res => setSeller(res.data))
        fetchArticles().catch(err => setError(err))
        // fetchDiscounts().catch(err => setError(err)).then(calculateDiscounts())
    },[])

    const {id} = useParams();

    const fetchArticles = async () => {
        try {
            if (AuthenticationService.getRole() === "SELLER") {
                const response = await ArticleService.getSellerArticles(id)
                setArticles(response.data);
            } else {
                const response = await ArticleService.getSellerArticles(id)
                setArticles(response.data);
            }
        } catch (error) {
            console.error(`Error while fetching articles: ${error}`);
        }
    }

    // const fetchDiscounts = async () => {
    //     const discounts = await DiscountService.getDiscounts(id);
    //     setDiscounts(discounts.data)
    // }

    // const calculateDiscounts = () => {
    //     let calculatedArticles = []
    //
    //     for (let i = 0; i < articles.length; i++) {
    //         let article = articles[i]
    //         for (let i = 0; i < discounts.length; i++) {
    //             let id = discounts[i].article.id
    //             if (id === article.id) {
    //                 console.log("ACTION")
    //                 article["price"] = 3000
    //             }
    //         }
    //         console.log(article)
    //         calculatedArticles.push(article)
    //     }
    //
    //     console.log(calculatedArticles)
    //     setArticles(calculatedArticles)
    // }

    const handleDelete = async (id) => {    // deletes chosen article
        await ArticleService.deleteArticle(id);
        setArticles((articles) => articles.filter((article) => article.id !== id));
    }

    const handleClickOpen = (articleId) => {    // opens quanity dialog
        setOpen(true);
        setArticle(articleId)
        console.log("Article id " + articleId)
    };

    const handleClose = () => {setOpen(false);};

    const handleAddToCart = () => {  // Adds article to cart
        let quanity = document.getElementById('quanity').value
        cartArticles.push({
            "quanity": quanity,
            "articleId": article
        })
        console.log("Article id " + article + " quanity: " + quanity)
        localStorage.setItem('cartItems', JSON.stringify(cartArticles))

        console.log(JSON.parse(localStorage.getItem('cartItems')))
        setOpen(false);
    }

    console.log(articles)
    console.log(discounts)
    console.log(discountsA)

    return (
            <div className={classes.root} class="card-view">

                {AuthenticationService.getRole() === "ROLE_BUYER" && (
                    <Button size="large" type="submit" color="inherit" href="/cart">
                        CART
                    </Button>
                )}
                <Grid
                    container
                    spacing={3}
                    direction="row"
                    justify="flex-start"
                    alignItems="flex-start"
                >
                    {articles.map(elem => (

                        <Grid item xs={12} sm={6} md={3} key={articles.indexOf(elem)}>
                                <Card variant="outlined">
                                    <CardMedia
                                        className={classes.media}
                                        image={"data:image/png;base64," + elem.imageBytes}
                                        title="Contemplative Reptile"
                                    />
                                    <CardContent>
                                        <Typography gutterBottom variant="h5" component="h1">
                                            {elem.name}
                                        </Typography>
                                        <Typography variant="body2" color="textSecondary" component="h2">
                                                <b> {elem.price} €</b>
                                        </Typography>
                                        <Typography variant="body2" color="textSecondary" component="p">
                                            {elem.description}
                                        </Typography>
                                    </CardContent>

                                    <CardActions style={{
                                        display: "flex",
                                        justifyContent: "center",
                                        alignItems: "center",
                                    }}>
                                    {AuthenticationService.getRole() === "ROLE_SELLER" && (
                                        <>
                                            <Button size="small" color="primary" href={"/editArticle/" + elem.id}>
                                                Edit
                                            </Button>
                                            <Button size="small" color="primary" onClick={() => handleDelete(elem.id)}>
                                                Delete
                                            </Button>
                                        </>
                                    )}
                                    {AuthenticationService.getRole() === "ROLE_BUYER" && (
                                            <Button size="small" type="submit" color="primary" onClick={() => handleClickOpen(elem.id)}>
                                                Add to cart
                                            </Button>
                                    )}
                                    </CardActions>
                                </Card>
                            </Grid>
                    ))}
                </Grid>

                <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                    <DialogContent>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="quanity"
                            label="Quanity"
                            type="number"
                            variant="outlined"
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={() => handleAddToCart()} color="primary">
                            Continue
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
    )
}
