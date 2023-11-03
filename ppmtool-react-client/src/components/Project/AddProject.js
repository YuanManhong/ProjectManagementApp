import React, { Component} from "react";
import {useState} from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import { useNavigate } from 'react-router-dom';

function AddProject(props) {
    const [state, setState] = useState({
      projectName: '',
      projectIdentifier: '',
      description: '',
      start_date: '',
      end_date: '',
    });const navigate = useNavigate();

    const onChange = (e) => {
      setState({ ...state, [e.target.name]: e.target.value });
    };
  
    const onSubmit = (e) => {
        e.preventDefault();
        const newProject = {
          projectName: state.projectName,
          projectIdentifier: state.projectIdentifier,
          description: state.description,
          start_date: state.start_date,
          end_date: state.end_date,
        };
        props.createProject(newProject, navigate);
      };
    return (
      <div>
        {
          //check name attribute input fields
          //create constructor
          //set state
          //set value on input fields
          //create onChange function
          //set onChange on each input field
          //bind on constructor
          //check state change in the react extension
        }

        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Project form</h5>
                <hr />
                <form onSubmit={onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg "
                      placeholder="Project Name"
                      name="projectName"
                      value={state.projectName}
                      onChange={onChange} 
                    />
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Unique Project ID"
                      name="projectIdentifier"
                      value={state.projectIdentifier}
                      onChange={onChange}
                    />
                  </div>
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg"
                      placeholder="Project Description"
                      name="description"
                      value={state.description}
                      onChange={onChange}
                    />
                  </div>
                  <h6>Start Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="start_date"
                      value={state.start_date}
                      onChange={onChange}
                    />
                  </div>
                  <h6>Estimated End Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="end_date"
                      value={state.end_date}
                      onChange={onChange}
                    />
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

AddProject.propTypes = {
  createProject: PropTypes.func.isRequired
};

export default connect(
    null,
    { createProject },
  )(AddProject);