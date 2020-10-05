const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MODE = process.env.NODE_ENV;
const enabledSourceMap = MODE === "development";
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const webpack = require('webpack');

// debug時の画像読み込み
const urlLoader = {
    test: /\.(png|jpe?g|gif)$/,
    loader: 'url-loader',
    options: {
    esModule: false
    }
}

  // build時の画像パス書き換え
const fileLoader = {
    test: /\.(jpg|png|gif)$/,
    use: [
    {
        loader: 'file-loader',
        options: {
        name: '[name].[ext]',
        outputPath: function(path, resource, context) {
            return `img/${path}`
        },
        publicPath: function(path, resource, context) {
            return `../img/${path}`
        },
        esModule: false
        }
    }
    ]
}

const imgLoader = enabledSourceMap ? urlLoader : fileLoader

module.exports = {
    mode: MODE,
    entry: './src/main/resources/front/src/js/index.js',

    output: {
        path: path.join(__dirname, '/src/main/resources/static/'),
        filename: 'js/[name].js',
        },

    module: {
        rules: [
            {
                test:/\.(sa|sc|c)ss$/,
                use: [
                    enabledSourceMap ? "style-loader" : MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            url: true,
                            sourceMap: enabledSourceMap,
                            importLoaders: 2
                        }
                    },
                    {
                        loader: "postcss-loader",
                        options: {
                            sourceMap: true,
                         postcssOptions: {
                          // PostCSS側でもソースマップを有効にする
                         
                          plugins: [
                            // Autoprefixerを有効化
                            // ベンダープレフィックスを自動付与する
                            require("autoprefixer")({ grid: true }),
                          ],
                        },
                        },
                      },
                    {
                        loader: "sass-loader",
                        options: {
                            sourceMap: enabledSourceMap
                        }
                    }
                ]
            },
            {
                test: /\.js$/,
                use: [
                    {
                        loader: 'babel-loader',
                        options: {
                            presets: ['@babel/preset-env'],
                        }
                    }
                ]
            },
            {
                test: /\.html$/,
                loader: 'html-loader'
            },
            imgLoader
        ]
    },
    plugins: [
        //login.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/login_origin.html',
            filename: '../templates/login.html'
        }),

        //admin.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/admin/admin_origin.html',
            filename: '../templates/admin.html'
        }),
        //index.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/index/index_origin.html',
            filename: '../templates/index.html'
        }),
        //tutor.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/tutor/tutor_origin.html',
            filename: '../templates/tutor.html'
        }),
        //viewer.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/viewer/viewer_origin.html',
            filename: '../templates/viewer.html'
        }),

        //user-management/edit.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/usermanagement/usermanagement_edit_origin.html',
            filename: '../templates/user-management/edit.html'
        }),
        //user-management/list.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/usermanagement/usermanagement_list_origin.html',
            filename: '../templates/user-management/list.html'
        }),
        //user-management/new.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/usermanagement/usermanagement_new_origin.html',
            filename: '../templates/user-management/new.html'
        }),

        //samplepage(admin)
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/admin/admin_sample1_origin.html',
            filename: '../templates/admin/sample1.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/admin/admin_sample2_origin.html',
            filename: '../templates/admin/sample2.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/admin/admin_sample3_origin.html',
            filename: '../templates/admin/sample3.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/admin/admin_sample4_origin.html',
            filename: '../templates/admin/sample4.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/admin/admin_sample5_origin.html',
            filename: '../templates/admin/sample5.html'
        }),

        //samplepage(index)
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/index/index_sample1_origin.html',
            filename: '../templates/index/sample1.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/index/index_sample2_origin.html',
            filename: '../templates/index/sample2.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/index/index_sample3_origin.html',
            filename: '../templates/index/sample3.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/index/index_sample4_origin.html',
            filename: '../templates/index/sample4.html'
        }),
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/index/index_sample5_origin.html',
            filename: '../templates/index/sample5.html'
        }),

        //profile/user.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/profile/user.html',
            filename: '../templates/profile/user.html'
        }),
        //profile/edit.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/profile/edit.html',
            filename: '../templates/profile/edit.html'
        }),

        //menu.html
        new HtmlWebpackPlugin({
            template: './src/main/resources/front/src/html/menu_origin.html',
            filename: '../templates/menu.html'
        }),

        new MiniCssExtractPlugin({
            filename: `css/[name].css`
        }),
        new webpack.ProvidePlugin({
            $: 'jquery'
        }),
    ],

    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        publicPath: '/',
        watchContentBase: true,
        port: 3000,
        inline: true,
        hot: true,
    },

    resolve: {
        modules: ['node_modules'],
        extensions: ['.js'],
    },
};