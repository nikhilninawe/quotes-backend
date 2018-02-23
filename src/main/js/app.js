'use strict';

import React, {Component} from 'react';
import ReactDOM from 'react-dom';
// const client = require('./client');
// end::vars[]

// tag::app[]
class App extends Component {

  constructor(props) {
    super(props);
    this.state = {quotes: []};
  }

  componentDidMount() {
    fetch(`http://localhost:8090/quote/en/10/false/false`)
      .then(result => result.json())
      .then(items => {
        this.setState({ quotes: items})
      })
      .catch(reason => {console.log(reason)});
  }

  render() {
    return (
        <QuoteList quotes={this.state.quotes}/>
      )
  }
}
// end::app[]

// tag::employee-list[]
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
    <th>Language</th>
    </tr>
    {employees}
  </tbody>
    </table>
  )
  }
}
// end::employee-list[]

// tag::employee[]
class Quote extends Component{
  handleClick() {
    console.log('The link was clicked.' + this.props.key);
  };
  render() {
    return (
      <tr>
      <td>{this.props.quote.id}</td>
      <td>{this.props.quote.type}</td>
      <td><img src={this.props.quote.quoteUrl} height={150} width={200} /></td>
        <td><button onClick={this.handleClick.bind(this)}> Approve </button></td>
    </tr>
  )
  }
}
// end::employee[]

// tag::render[]
ReactDOM.render(
  <App />,
  document.getElementById('react')
)