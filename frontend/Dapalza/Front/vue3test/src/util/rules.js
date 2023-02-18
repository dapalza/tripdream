const user_ids_rule = (input_id) => {
  const validateId = /^[a-zA-Z0-9]*$/

  if(!validateId.test(input_id) || !input_id){
    return false;
  }
  return true;
}

export {user_ids_rule}