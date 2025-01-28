import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'

class add_tutorialcomponent extends Component{
  constructor(props){
    super(props);

  }

  render(){
    return(
      <div>
          <Form.Group>
            <div>
              </div>            
              <Form.Label>Title</Form.Label>
            <Form.Control type="text" placeholder="Enter title" />
          </Form.Group>
      
        </div>    
      )
    
  }
}

export default add_tutorialcomponent;