// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer:{
//     proxy:{
//       '/api':{
//         // target: 'http://fleescape.shop:8082',
//        target: 'http://localhost:8082',
//         changeOrigin: true,
//       }
//     }
//   },
//   pluginOptions: {
//     vuetify: {
// 			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
// 		}
//   }
// })



// const { defineConfig } = require('@vue/cli-service')
module.exports={
  transpileDependencies: true,
  devServer:{
    proxy:{
      '/api' : {
        // "target": 'http://fleescape.shop:8082',
        "target": 'http://localhost:8082',
        "changeOrigin": true,
        "pathRewrite":{
          // '^/api' : ''
        }
      }
    }
  },
  pluginOptions: {
    vuetify: {
			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
		}
  }
}