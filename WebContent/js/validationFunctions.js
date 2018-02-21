/**
 * http://usejsdoc.org/
 */

function resetErrors(obj) {
  obj.newUserNameOk = true;
  obj.newEmailOk = true;
  obj.newStreetOk = true;
  obj.newBlockNumberOk = true;
  obj.newCityOk = true;
  obj.newZIPOk = true;
  obj.newPhoneOk = true;
  obj.newPassOk = true;
  obj.newPass2Ok = true;
  obj.newNickOk = true;
  obj.newDescOk = true;
  obj.newPhotoOk = true;
}

function testOldUsername(str) {
  var len = str.length;
  if (len > 10) return false;
  var regex1 = /^[a-zA-Z0-9]+$/;
  if (!regex1.test(str)) return false;
  return true;
}


function testNewUsername(str) {
  var len = str.length;
  if (len > 10) return false;
  var regex1 = /^[a-zA-Z0-9]+$/;
  if (!regex1.test(str)) return false;
  /* TODO test for duplicate user in database */
  return true;
}

function testNewEmail(str) {
  var parts = str.split('@');
  if (parts[1] == null || parts[1].len <= 0) {
    return false;
  }
  return true;
}
function testNewStreet(str) {
  var len = str.length;
  if (len < 4) return false;
  var regex1 = /^[a-zA-Z]+$/;
  if (!regex1.test(str)) {
    return false;
  }
  return true;
}

function testNewBNum(num) {
  var regex1 = /^[0-9]+$/;
  if (!regex1.test(num)) {
    return false;
  }
  return true;
}

function testNewZip(num) {
  var regex1 = /^[0-9]+$/;
  if (!regex1.test(num)) {
    return false;
  }
  if (num.toString().length != 7) return false;
  return true;
}

function testNewPhone(num2) {
  var num = num2.toString();
  num = num.replace(/-/gi, '');
  num = num.replace(/ /gi, '');
  var regex1 = /^[0-9]+$/;
  if (!regex1.test(num)) {
    return false;
  }
  var nmbrs = num.split('');
  if (nmbrs[0] != '0') {
    return false;
  }
  if (nmbrs[1] != '5' && num.length != 9) {
    return false;
  }
  if (nmbrs[1] == '5' && num.length != 10) {
    return false;
  }
  return true;
}

function reformatPhone(num2){
  var num = num2.toString();
  num = num.replace(/-/gi, '');
  num = num.replace(/ /gi, '');
  var nmbrs = num.split('');
  var i = 0;
  var k = 0;
  var j = nmbrs[1] == '5' ? 13 : 11;
  var formNum = '';
  for (i = 0; i < j; i++) {
    if (i == 3 || i == 7) {
      formNum = formNum + '-';
    }
    else if (i == 10 && j == 13) {
      formNum = formNum + '-';
    }
    else {
      formNum = formNum + nmbrs[k];
      k++;
    }
  }
  return formNum;
}

function testNewPassword(str) {
  var len = str.length;
  if (len > 8) return false;
  return true;
}

function testNewNick(str){
  var len=str.length;
  if (len > 20) return false;
  /*TODO test for uniqueness !!! */
  return true;
}

function testNewDescription(str) {
  var len = str.length;
  if (len > 50) return false;
  return true;
}
function testNewPhoto(str){
  /* not sure how to test */
  
  return true;
}

function testFullName(str) {
  var len = str.length;
  if (len < 4) return false;
  var regex1 = /^[a-z ,.'-]+$/i;
  if (!regex1.test(str)) {
    return false;
  }
  return true;
}

function testCountry(str) {
  var len = str.length;
  if (len < 2) return false;
  var regex1 = /^[a-zA-Z]+$/;
  if (!regex1.test(str)) {
    return false;
  }
  return true;
}

function testCardNum(str)
{
  if (str.length == 0) return false;
  if (str.startsWith('34') && str.length == 15){return true;}
  if (str.startsWith('4') && str.length == 16) {return true;}
  return false;
}

function testCVV(card, cvv) {
  if (card.length == 0 || cvv.length == 0) return false;
  if (card.startsWith('34') && cvv.length == 4) return true;
  if (card.startsWith('4') && cvv.length == 3) return true;
  }

function markErrorSuccess(result,tag)
{
  if (result) {
    $(tag).removeClass('has-error').addClass('has-success');
  }
  else {
    $(tag).removeClass('has-success').addClass('has-error');
  }
}

function listProperties(obj) {
  var keys = [];
  for (var key in obj) {
    keys.push(key);
  }
  alert(keys);
}

function listPropertiesConsole(obj) {
  var keys = [];
  for (var key in obj) {
    keys.push(key);
  }
  console.log(keys);
}
