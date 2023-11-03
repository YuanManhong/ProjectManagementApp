// src/components/Project/EditProject.js
import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { getProject, updateProject } from "../../actions/projectActions";
import { useNavigate, useParams } from 'react-router-dom';

const EditProject = (props) => {
    const { projectIdentifier } = useParams();
    const navigate = useNavigate();
    const [state, setState] = useState({
        projectName: '',
        description: '',
        startDate: '',
        endDate: '',
    });

    const [errors, setErrors] = useState({});

    useEffect(() => {
        props.getProject(projectIdentifier, navigate);
    }, [projectIdentifier]);

    useEffect(() => {
        console.log('props.project:', props.project);
        console.log('props.errors:', props.errors);
        if(props.project.project){
            setState({
                ...state,
                ...props.project.project
            });
        }
        if(props.errors){
            setErrors(props.errors);
        }
    }, [props.project, props.errors]);

    const onChange = (e) => {
        console.log('event:', e);
        setState({ ...state, [e.target.name]: e.target.value });
        console.log('updated state:', state);
    };

    const validateForm = () => {
        let formErrors = {};

        if (!state.projectName) formErrors.projectName = "Project name is required";
        if (!state.description) formErrors.description = "Project description is required";
        if (!state.startDate) formErrors.startDate = "Start date is required";
        if (!state.endDate) formErrors.endDate = "Estimated end date is required";

        return formErrors;
    };

    const onSubmit = (e) => {
        e.preventDefault();

        const formErrors = validateForm();
        if (Object.keys(formErrors).length > 0) {
            setErrors(formErrors);  // 如果有错误，更新errors state
            return;  // 不要继续提交表单
        }

        const updatedProject = {
            ...state,
            projectIdentifier: props.project.project.projectIdentifier  // Ensure the project id is included in the update request
        };
        console.log('updatedProject:', updatedProject);
        props.updateProject(updatedProject, navigate);
    };

    return (
        <div>
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Edit Project form</h5>
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
                                        name="startDate"
                                        value={state.startDate}
                                        onChange={onChange}
                                    />
                                    {errors.startDate && <div className="alert alert-danger">{errors.startDate}</div>}
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group mb-2">
                                    <input
                                        type="date"
                                        className="form-control form-control-lg"
                                        name="endDate"
                                        value={state.endDate}
                                        onChange={onChange}
                                    />
                                    {errors.endDate && <div className="alert alert-danger">{errors.endDate}</div>}
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
};

EditProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    updateProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    project: state.project,
    errors: state.errors
});

export default connect(
    mapStateToProps,
    { getProject, updateProject }
)(EditProject);
