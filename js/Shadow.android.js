import React, {
  PureComponent,
} from 'react'

import {
  View,
  ViewPropTypes,
  requireNativeComponent,
} from 'react-native'

import PropTypes from 'prop-types'

class Shadow extends PureComponent {

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
