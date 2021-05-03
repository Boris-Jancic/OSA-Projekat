import React, {Component} from "react";

class AddArticle extends Component {
    render() {
        return(
            <>
                <form className="form-size" method="POST" action="login">
                    <h1>Add article</h1>

                    <input className="form-control input-margin" id="name" type="text" placeholder="Name"/>
                    <input className="form-control input-margin" id="picture" type="text" placeholder="Pictue"/>
                    <input className="form-control input-margin" id="description" type="text" placeholder="Description"/>
                    <input className="form-control input-margin" id="price" type="number" placeholder="Price"/>

                    <input className="form-control btn input-margin" value="Submit" onClick={this.addArticle} />
                </form>
            </>
        )
    }
}

export default AddArticle