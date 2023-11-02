import React, { Component } from 'react'

class AddProject extends Component {
    constructor(){
        super()
        this.state={ //state is immutable, so you need to send a new state when user enter something
            "projectName": "",
            "projectIdentifier": "",
            "description": "",
            "startDate": "",
            "endDate": "",
        };
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(e){
        this.setState({[e.target.name]: e.target.value}) // set a new state
    }

    onSubmit(e){
        e.preventDefault(); // prevent reload the page which causing lose data
        const newProject ={
            "projectName": this.state.projectName,
            "projectIdentifier": this.state.projectIdentifier,
            "description": this.state.description,
            "startDate": this.state.startDate,
            "endDate": this.state.endDate,
        };
        console.log(newProject);
    }

  render() {
    return (
        <div className="register">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Create Project form</h5>
                    <hr />
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group mb-2">
                            <input 
                                type="text" 
                                className="form-control form-control-lg " 
                                placeholder="Project Name" 
                                name='projectName'
                                value={this.state.projectName}
                                onChange={this.onChange.bind(this)}
                                />
                        </div>
                        <div className="form-group mb-2">
                            <input 
                                type="text" 
                                className="form-control form-control-lg" 
                                placeholder="Unique Project ID" 
                                name='projectIdentifier'
                                value={this.state.projectIdentifier}
                                onChange={this.onChange.bind(this)}
                            />
                        </div>
                        <div className="form-group mb-2">
                            <textarea 
                                className="form-control form-control-lg" 
                                placeholder="Project Description" 
                                name='description'
                                value={this.state.description}
                                onChange={this.onChange.bind(this)}
                            ></textarea>
                        </div>
                        <h6>Start Date</h6>
                        <div className="form-group mb-2">
                            <input 
                                type="date" 
                                className="form-control form-control-lg" 
                                name="startDate"
                                value={this.state.startDate}
                                onChange={this.onChange.bind(this)} />
                        </div>
                        <h6>Estimated End Date</h6>
                        <div className="form-group mb-2">
                            <input 
                                type="date" 
                                className="form-control form-control-lg" 
                                name="endDate" 
                                value={this.state.endDate}
                                onChange={this.onChange.bind(this)}
                                />
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>

    )
  }
}

export default AddProject;
