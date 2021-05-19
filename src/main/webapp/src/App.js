import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavbarComponent from "./components/navbar/NavbarComponent";
import HomeLayout from "./layouts/HomeLayout";
import LoginLayout from "./layouts/LoginLayout";
import BrowseLayout from "./layouts/BrowseLayout";
import EditArticle from "./components/crud/EditArticleComponent";
import AddArticle from "./components/crud/AddArticleComponent";
import {PrivateRoute} from "./components/PrivateRoute";
import NotFound from "./layouts/NotFound";
import Register from "./components/crud/RegisterComponent";
import UserProfile from "./components/view/UserProfile";
import AdminLayout from "./layouts/AdminLayout";
import SellerComponent from "./components/view/SellerComponent";
import CartItemsTable from "./components/tables/CartItemsTable";

function App() {
  return (
      <div className="App">
      <NavbarComponent />
        <Router>
          <Switch>
              <Route path="/home" exact component={HomeLayout}/>
              <Route path="/register" exact component={Register}/>
              <Route path="/login" exact component={LoginLayout}/>
              <Route path="/profile" exact component={UserProfile}/>
              <PrivateRoute
                  path="/sellers"
                  exact
                  component={SellerComponent}
                  roles={["ROLE_BUYER"]}/>
              <PrivateRoute
                  path="/cart"
                  exact
                  component={CartItemsTable}
                  roles={["ROLE_BUYER"]}/>
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
              <PrivateRoute
                  path="/users"
                  exact
                  component={AdminLayout}
                  roles={["ROLE_ADMIN"]}/>
              <Route path="/browse/:id" exact component={BrowseLayout}/>
              <Route component={NotFound} />
          </Switch>
        </Router>
      </div>
  );
}

export default App;
