import React, {
  PureComponent,
} from 'react'

import {
  View,
} from 'react-native'

export default class Shadow extends PureComponent {

  static propTypes = {
    width: PropTypes.number.isRequired,
    height: PropTypes.number.isRequired,
    backgroundColor: PropTypes.string.isRequired,
    borderRadius: PropTypes.number.isRequired,
    shadowColor: PropTypes.string.isRequired,
    shadowOffsetX: PropTypes.number.isRequired,
    shadowOffsetY: PropTypes.number.isRequired,
    shadowRadius: PropTypes.number.isRequired,
  }

  render() {

    let {
      width,
      height,
      ...props
    } = this.props

    return (
      <View
        style={{
          width,
          height,
        }}
      >
        <View
          style={props}
        />
      </View>
    )

  }

}