import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import NavbarComponent from "./components/NavbarComponent";
import RegisterLayout from "./layouts/RegisterLayout";
import HomeLayout from "./layouts/HomeLayout";
import LoginLayout from "./layouts/LoginLayout";
import BrowseLayout from "./layouts/BrowseLayout";
import 'bootstrap/dist/css/bootstrap.min.css';
import EditArticle from "./components/EditArticleComponent";
import AddArticle from "./components/AddArticleComponent";
import {PrivateRoute} from "./components/PrivateRoute";
import NotFound from "./layouts/NotFound";

function App() {
  return (
      <div className="App">
      <NavbarComponent />
        <Router>
          <Switch>
              <Route path="/home" exact component={HomeLayout}/>
              <Route path="/register" exact component={RegisterLayout}/>
              <Route path="/login" exact component={LoginLayout}/>
              <PrivateRoute
                  path="/addArticle"
                  exact
                  component={AddArticle}
                  roles={["ROLE_SELLER"]}/>
              <PrivateRoute
                  path="/editArticle/:id"
                  exact
                  component={EditArticle}
                  roles={["ROLE_SELLER"]}/>
              <Route path="/browse" exact component={BrowseLayout}/>
              <Route component={NotFound} />
          </Switch>
        </Router>
      </div>
  );
}

export default App;
