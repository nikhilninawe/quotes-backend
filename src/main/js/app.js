'use strict';

import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Dropdown from 'react-dropdown'

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {quotes: [], selected: 'en'};
  }

  componentDidMount() {
    fetch(`/quote/en/10/false/false`)
      .then(result => result.json())
      .then(items => {
        this.setState({ quotes: items})
      })
      .catch(reason => {console.log(reason)});
  }

  _onSelect(val){
    fetch(`/quote/${val.value}/10/false/false`)
      .then(result => result.json())
      .then(items => {
        this.setState({ quotes: items, selected: val.value})
      })
      .catch(reason => {console.log(reason)});
  }

  _onSelect2(){
    fetch(`/quote/${this.state.selected}/10/false/false`)
      .then(result => result.json())
      .then(items => {
        this.setState({ quotes: items })
      })
      .catch(reason => {console.log(reason)});
  }

  handleClick(){
    this._onSelect({ value: this.state.selected });
  }


  render() {
    const options = [
      'en', 'hi', 'ru', 'mr'
    ];
    const defaultOption = this.state.selected;

    return (
      <div>
        <button onClick={this.handleClick.bind(this)}> Refresh </button>
        <Dropdown options={options} onChange={this._onSelect.bind(this)} value={defaultOption} placeholder="Select an option" />
        <QuoteList quotes={this.state.quotes} handle={this._onSelect2.bind(this)} />
        <button onClick={this.handleClick.bind(this)}> Refresh </button>
      </div>
    )
  }
}

class QuoteList extends Component{
  render() {
    var employees = this.props.quotes.map(q =>
      <Quote key={q.id} quote={q} handle={this.props.handle}/>
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
    this.props.handle();
    fetch(`/quote/true/${this.props.quote.id}`, {
      method: 'POST'
    });
    this.setState({approve: true})
  };

  reject() {
    this.props.handle();
    fetch(`/quote/false/${this.props.quote.id}`, {
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
        <td><button onClick={this.handleClick.bind(this)}> {this.state.approve ? 'Done':'Approve'} </button></td>
        <td><button onClick={this.reject.bind(this)}> {this.state.approve ? 'Done':'Reject'} </button></td>
      </tr>
  )
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('react')
)