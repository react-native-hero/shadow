import React, {
  PureComponent,
} from 'react'

import {
  View,
  ViewStyle,
  requireNativeComponent,
} from 'react-native'

import PropTypes from 'prop-types'

class Shadow extends PureComponent {

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
      children,
      style,
      ...props
    } = this.props

    let top = shadowRadius - shadowOffsetY
    let right = shadowRadius + shadowOffsetX
    let bottom = shadowRadius + shadowOffsetY
    let left = shadowRadius - shadowOffsetX

    let shadow = (
      <RNTShadow
        shadowOffsetX={shadowOffsetX}
        shadowOffsetY={shadowOffsetY}
        shadowRadius={shadowRadius}
        {...props}
        style={{
          width: width + left + right,
          height: height + top + bottom,
        }}
      />
    )

    if (!children) {
      return shadow
    }

    let viewStyle = {
      position: 'absolute',
      width,
      height,
      top,
      left,
    }

    if (style) {
      viewStyle = [viewStyle, style]
    }

    return (
      <View>
        {shadow}
        <View
          style={viewStyle}
        >
          {children}
        </View>
      </View>
    )

  }

}

const RNTShadow = requireNativeComponent('RNTShadow', Shadow)

export default Shadow
