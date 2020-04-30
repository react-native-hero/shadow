# @react-native-hero/shadow

## Getting started

Install the library using either Yarn:

```
yarn add @react-native-hero/shadow
```

or npm:

```
npm install --save @react-native-hero/shadow
```

## Link

For android, the package will be linked automatically on build.

- React Native <= 0.59

run the following command to link the package:

```
$ react-native link @react-native-hero/shadow
```

## Example

```js
import {
  Shadow
} from '@react-native-hero/shadow'

<Shadow
  width={100}
  height={100}
  shadowRadius={10}
  // The shadow color must be rgba
  shadowColor="rgba(0,0,0,0.5)"

  // optional
  shadowOffsetX={0}
  shadowOffsetY={0}
  borderRadius={0}
  backgroundColor="transparent"
/>
```
