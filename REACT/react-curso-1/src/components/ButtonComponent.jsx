
import PropTypes from 'prop-types';

function ButtonComponent(props) {
  return (
    <button onClick={props.suma}>Soy gilipollas</button>
  )
}

ButtonComponent.propTypes = {
  suma: PropTypes.func.isRequired,
};

export default ButtonComponent