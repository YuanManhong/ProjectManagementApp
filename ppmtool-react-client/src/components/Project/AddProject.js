import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import {createProject} from "../../actions/projectActions";
import { useNavigate } from 'react-router-dom';


const AddProject = (props) => {
    const [state, setState] = useState({
        projectName: '',
        projectIdentifier: '',
        description: '',
        start_date: '',
        end_date: '',
    });

    const [errors, setErrors] = useState({});  // 添加一个新的state来存储错误消息
    useEffect(() => {
      if(props.errors){
          setErrors(props.errors);
      }
    }, [props.errors]);
    const navigate = useNavigate();

    const onChange = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    };

    const validateForm = () => {
        let formErrors = {};

        if (!state.projectName) formErrors.projectName = "Project name is required";
        if (!state.projectIdentifier || state.projectIdentifier.length < 4 || state.projectIdentifier.length > 5) {
            formErrors.projectIdentifier = "Please use 4 to 5 characters for project identifier";
        }
        if (!state.description) formErrors.description = "Project description is required";
        if (!state.start_date) formErrors.start_date = "Start date is required";
        if (!state.end_date) formErrors.end_date = "Estimated end date is required";

        return formErrors;
    };

    const onSubmit = (e) => {
        e.preventDefault();

        const formErrors = validateForm();
        if (Object.keys(formErrors).length > 0) {
            setErrors(formErrors);  // 如果有错误，更新errors state
            return;  // 不要继续提交表单
        }

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
          <div className="project">
              <div className="container">
                  <div className="row">
                      <div className="col-md-8 m-auto">
                          <h5 className="display-4 text-center">Create Project form</h5>
                          <hr />
                          <form onSubmit={onSubmit}>
                              <div className="form-group mb-2">
                                  <input
                                      type="text"
                                      className="form-control form-control-lg "
                                      placeholder="Project Name"
                                      name="projectName"
                                      value={state.projectName}
                                      onChange={onChange}
                                  />
                                  {errors.projectName && <div className="alert alert-danger">{errors.projectName}</div>}
                              </div>
                              <div className="form-group mb-2">
                                  <input
                                      type="text"
                                      className="form-control form-control-lg"
                                      placeholder="Unique Project ID"
                                      name="projectIdentifier"
                                      value={state.projectIdentifier}
                                      onChange={onChange}
                                  />
                                  {errors.projectIdentifier && <div className="alert alert-danger">{errors.projectIdentifier}</div>}
                              </div>
                              <div className="form-group mb-2">
                                  <textarea
                                      className="form-control form-control-lg"
                                      placeholder="Project Description"
                                      name="description"
                                      value={state.description}
                                      onChange={onChange}
                                  />
                                  {errors.description && <div className="alert alert-danger">{errors.description}</div>}
                              </div>
                              <h6>Start Date</h6>
                              <div className="form-group mb-2">
                                  <input
                                      type="date"
                                      className="form-control form-control-lg"
                                      name="start_date"
                                      value={state.start_date}
                                      onChange={onChange}
                                  />
                                  {errors.start_date && <div className="alert alert-danger">{errors.start_date}</div>}
                              </div>
                              <h6>Estimated End Date</h6>
                              <div className="form-group mb-2">
                                  <input
                                      type="date"
                                      className="form-control form-control-lg"
                                      name="end_date"
                                      value={state.end_date}
                                      onChange={onChange}
                                  />
                                  {errors.end_date && <div className="alert alert-danger">{errors.end_date}</div>}
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
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired  // 添加对props.errors的PropTypes验证
};

const mapStateToProps = state => ({
  errors: state.errors  // 从Redux store中获取errors
});

export default connect(
  mapStateToProps,
  { createProject },
)(AddProject);




  /*
  为什么props可以点出来createProject:
  在你提供的代码中，connect函数被用来连接AddProject组件到Redux store，并将createProject action映射到组件的props。这是通过connect函数的第二个参数mapDispatchToProps完成的。
  */

  // default export and named export
  // import createProject from "../../actions/projectActions";:

  // This syntax is used for importing the default export from the module '../../actions/projectActions'. There can only be one default export per module.
  // import {createProject} from "../../actions/projectActions";:
  
  // This syntax is used for importing a named export called createProject from the module '../../actions/projectActions'. A module can have multiple named exports.
  // In summary, the first syntax is used for importing the default export from a module, while the second syntax is used for importing a specific named export from a module.