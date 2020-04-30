import React, {
  PureComponent,
} from 'react'

import {
  View,
} from 'react-native'

import PropTypes from 'prop-types'

export default class Shadow extends PureComponent {

  static propTypes = {
    width: PropTypes.number.isRequired,
    height: PropTypes.number.isRequired,
    backgroundColor: PropTypes.string,
    borderRadius: PropTypes.number,
    shadowColor: PropTypes.string.isRequired,
    shadowOffsetX: PropTypes.number.isRequired,
    shadowOffsetY: PropTypes.number.isRequired,
    shadowRadius: PropTypes.number.isRequired,
  }

  static defaultProps = {
    shadowOffsetX: 0,
    shadowOffsetY: 0,
  }

  render() {

    let {
      width,
      height,
      shadowOffsetX,
      shadowOffsetY,
      shadowRadius,
      ...props
    } = this.props

    let top = shadowRadius - shadowOffsetY
    let right = shadowRadius + shadowOffsetX
    let bottom = shadowRadius + shadowOffsetY
    let left = shadowRadius - shadowOffsetX

    return (
      <View
        style={{
          width,
          height,
        }}
      >
        <View
          style={{
            width: width - left - right,
            height: height - top - bottom,
            marginTop: top,
            marginLeft: left,
            shadowRadius,
            shadowOpacity: 1,
            shadowOffset: {
              width: shadowOffsetX,
              height: shadowOffsetY,
            },
            ...props,
          }}
        />
      </View>
    )

  }

}