import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavbarComponent from "./components/NavbarComponent";
import HomeLayout from "./layouts/HomeLayout";
import LoginLayout from "./layouts/LoginLayout";
import BrowseLayout from "./layouts/BrowseLayout";
import EditArticle from "./components/EditArticleComponent";
import AddArticle from "./components/AddArticleComponent";
import {PrivateRoute} from "./components/PrivateRoute";
import NotFound from "./layouts/NotFound";
import Register from "./components/RegisterComponent";
import UserProfile from "./components/UserProfile";
import {UserTable} from "./components/UserTable";
import AdminLayout from "./layouts/AdminLayout";

function App() {
  return (
      <div className="App">
      <NavbarComponent />
        <Router>
          <Switch>
              <Route path="/home" exact component={HomeLayout}/>
              <Route path="/register" exact component={Register}/>
              <Route path="/login" exact component={LoginLayout}/>
              <PrivateRoute
                  path="/addArticle"
                  exact
                  component={AddArticle}
                  roles={["ROLE_SELLER"]}/>
              <PrivateRoute
                  path="/users"
                  exact
                  component={AdminLayout}
                  roles={["ROLE_ADMIN"]}/>
              <PrivateRoute
                  path="/editArticle/:id"
                  exact
                  component={EditArticle}
                  roles={["ROLE_SELLER"]}/>
              <Route path="/browse" exact component={BrowseLayout}/>
              <Route path="/profile" exact component={UserProfile}/>
              <Route component={NotFound} />
          </Switch>
        </Router>
      </div>
  );
}

export default App;
