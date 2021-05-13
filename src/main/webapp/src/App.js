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

function App() {
  return (
      <div className="App">
      <NavbarComponent />
        <Router>
          <Switch>
              <Route path="/home" exact component={HomeLayout}/>
              <Route path="/register" exact component={RegisterLayout}/>
              <Route path="/login" exact component={LoginLayout}/>
              <Route path="/addArticle" exact component={AddArticle}/>
              <Route path="/editArticle/:id" exact component={EditArticle}/>
              <Route path="/browse" exact component={BrowseLayout}/>
          </Switch>
        </Router>
      </div>
  );
}

export default App;
