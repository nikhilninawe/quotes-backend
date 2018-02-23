'use strict';

import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Dropdown from 'react-dropdown'

// const client = require('./client');
// end::vars[]

// tag::app[]
class App extends Component {

  constructor(props) {
    super(props);
    this.state = {quotes: [], selected: 'en'};
  }

  componentDidMount() {
    fetch(`http://localhost:8090/quote/en/10/false/false`)
      .then(result => result.json())
      .then(items => {
        this.setState({ quotes: items})
      })
      .catch(reason => {console.log(reason)});
  }

  _onSelect(val){
    fetch(`http://localhost:8090/quote/${val.value}/10/false/false`)
      .then(result => result.json())
      .then(items => {
        this.setState({ quotes: items})
      })
      .catch(reason => {console.log(reason)});
    this.setState({ selected: val.value})
  }

  render() {
    const options = [
      'en', 'hi', 'ru', 'mr'
    ];
    const defaultOption = this.state.selected;

    return (
      <div>
        <Dropdown options={options} onChange={this._onSelect.bind(this)} value={defaultOption} placeholder="Select an option" />
        <QuoteList quotes={this.state.quotes} />
      </div>
    )
  }
}

class QuoteList extends Component{
  render() {
    var employees = this.props.quotes.map(q =>
      <Quote key={q.id} quote={q}/>
  );
    return (
      <table>
        <tbody>
       <tr>
      <th>Id</th>
    <th>Type</th>
    <th>Image</th>
    </tr>
    {employees}
  </tbody>
    </table>

  )
  }
}

class Quote extends Component{
  constructor(props) {
    super(props);
    this.state = {approve: false};
  }

  handleClick() {
    fetch(`http://localhost:8090/quote/approve/${this.props.quote.id}`, {
      method: 'POST'
    });
    this.setState({approve: true})
  };

  render() {
      let image = null
      if(this.props.quote.type === "image"){
        image = (<img src={this.props.quote.quoteUrl} height={150} width={200} />);
      }else {
        image = this.props.quote.quote;
      }

    return (
      <tr>
      <td>{this.props.quote.id}</td>
      <td>{this.props.quote.type}</td>
      <td>{image}</td>
        <td><button onClick={this.handleClick.bind(this)}> {this.state.approve ? 'Done':'Approve?'} </button></td>
    </tr>
  )
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('react')
)