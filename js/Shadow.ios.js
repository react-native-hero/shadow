import React, {
  PureComponent,
} from 'react'

import {
  View,
  ViewPropTypes,
} from 'react-native'

import PropTypes from 'prop-types'

export default class Shadow extends PureComponent {

  static propTypes = {
    width: PropTypes.number.isRequired,
    height: PropTypes.number.isRequired,
    shadowColor: PropTypes.string.isRequired,
    shadowRadius: PropTypes.number.isRequired,
    shadowOffsetX: PropTypes.number,
    shadowOffsetY: PropTypes.number,
    backgroundColor: PropTypes.string,
    borderRadius: PropTypes.number,
    style: ViewPropTypes.style,
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
      children,
      style,
      ...props
    } = this.props

    let top = shadowRadius - shadowOffsetY
    let right = shadowRadius + shadowOffsetX
    let bottom = shadowRadius + shadowOffsetY
    let left = shadowRadius - shadowOffsetX

    let viewStyle = {
      width,
      height,
      marginTop: top,
      marginLeft: left,
      shadowRadius,
      shadowOpacity: 1,
      shadowOffset: {
        width: shadowOffsetX,
        height: shadowOffsetY,
      },
      ...props,
    }

    if (style) {
      viewStyle = [viewStyle, style]
    }

    return (
      <View
        style={{
          width: width + left + right,
          height: height + top + bottom,
        }}
      >
        <View
          style={viewStyle}
        >
          {children}
        </View>
      </View>
    )

  }

}