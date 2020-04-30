import React, {
  PureComponent,
} from 'react'

import {
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

  render() {

    let {
      width,
      height,
      ...props
    } = this.props

    return (
      <RNTShadow
        {...props}
        style={{
          width,
          height,
        }}
      />
    )

  }

}

const RNTShadow = requireNativeComponent('RNTShadow', Shadow)

export default Shadow
