import React from 'react'
import { Link } from 'react-router-dom';

const CreateProjectBtn = () => {
  return (
    <Link to="/addProject" className="btn btn-lg btn-info">
        Create a Project
    </Link>
  )
}

export default CreateProjectBtn;

