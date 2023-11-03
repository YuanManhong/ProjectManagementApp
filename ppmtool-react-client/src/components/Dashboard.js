import React, { useEffect } from 'react';
import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types";
import ProjectItem from './Project/ProjectItem';
import CreateProjectBtn from './Project/CreateProjectBtn';


const Dashboard = (props) => {
    const { getProjects, project } = props;

    useEffect(() => {
        getProjects();
    }, [getProjects]);

    return (
        <div className="projects">
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <h1 className="display-4 text-center">Projects</h1>
                    <br />
                    <CreateProjectBtn/>
                    <br />
                    <hr />
                    {project.projects.map(project => (
                            <ProjectItem key={project.id} project={project} />
                    ))}

                </div>
            </div>
        </div>
    </div>

    )
}
Dashboard.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    project: state.project
});

export default connect(
    mapStateToProps,
    { getProjects }
)(Dashboard);