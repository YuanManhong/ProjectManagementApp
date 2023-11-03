import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter} from 'react-router-dom';
import {Routes, Route, Navigate} from "react-router";
import AddProject from './components/Project/AddProject';
import {Provider} from "react-redux";
import store from './store';
import EditProject from "./components/Project/EditProject";

function App() {
  return (
    <Provider store={store}>
    <BrowserRouter>
      <div className="App">
        <Header/>
        <Routes>
          <Route path="/" element={<Navigate to="/dashboard"/>}/>
          <Route path="/dashboard" element={<Dashboard/>}/>
          <Route path="/addProject" element={<AddProject/>}/>
          <Route path="/editProject/:projectIdentifier" element={<EditProject />} />  
        </Routes>
      </div>
    </BrowserRouter>
</Provider>
  );
}

export default App;
