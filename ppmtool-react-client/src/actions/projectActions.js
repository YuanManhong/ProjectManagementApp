import axios from 'axios';
import { GET_ERRORS } from './types';
import { GET_PROJECTS } from './types';
import { GET_PROJECT } from './types';
import { DELETE_PROJECT } from './types';

const apiBaseURL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8081';

export const createProject = (project, navigate) => async dispatch => {

    try {
        const res = await axios.post(`${apiBaseURL}/api/projects`, project);
        navigate('/dashboard');
    } catch (err) {
        console.error(err.response);  // 使用console.error而不是console.log，以便在控制台中清楚地显示错误
        dispatch({
            type: GET_ERRORS,
            payload:{ projectIdentifier: err.response.data.message },
        });
    }
};

export const getProjects = () => async dispatch => {
    try {
        const res = await axios.get(`${apiBaseURL}/api/projects`);
        dispatch({
            type: GET_PROJECTS,
            payload: res.data
        });
    } catch (err) {
        console.error(err);
    }
};

export const getProject = (id, navigate) => async dispatch => {
    try {
        const res = await axios.get(`${apiBaseURL}/api/projects/${id}`);
        dispatch({
            type: GET_PROJECT,
            payload: res.data
        });
    } catch (err) {
        navigate('/dashboard');
    }
};

export const updateProject = (project, navigate) => async dispatch => {
    console.log('updatedProject in action:', project);
    try {
        const res = await axios.put(`${apiBaseURL}/api/projects/${project.projectIdentifier}`, project);
        navigate(`/dashboard`);
    } catch (err) {
        console.error(err.response);
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const deleteProject = (projectIdentifier, navigate) => async dispatch => {
    try {
        await axios.delete(`${apiBaseURL}/api/projects/${projectIdentifier}`);
        dispatch({
            type: DELETE_PROJECT,
            payload: projectIdentifier
        });
        navigate('/dashboard');
    } catch (err) {
        console.error(err);
    }
};