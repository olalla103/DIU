module.exports = {
    env: {
        browser: true,
        es2021: true,
    },
    extends: [
        'eslint:recommended',
        'plugin:react/recommended',
        'plugin:react-hooks/recommended',
        'plugin:jsx-a11y/recommended',
        'plugin:prettier/recommended'
    ],
    parserOptions: {
        ecmaFeatures: {
            jsx: true,
        },
        ecmaVersion: 'latest',
        sourceType: 'module',
    },
    plugins: ['react', 'react-hooks', 'jsx-a11y'],
    rules: {
        'react/react-in-jsx-scope': 'off', // React 17+ no requiere importación explícita de React
        'react/prop-types': 'off',        // Desactiva las validaciones de prop-types
        'prettier/prettier': 'error',     // Asegúrate de que Prettier esté activado
    },
    settings: {
        react: {
            version: 'detect', // Detecta automáticamente la versión de React
        },
    },
    "react/prop-types": "off"
};
