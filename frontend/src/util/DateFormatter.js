const FormatDate = function (date) {
  var time = new Array();
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  time.push(year);
  if (month < 10) {
    month = "0" + month;
  }
  time.push(month);
  if (day < 10) {
    day = "0" + day;
  }
  time.push(day);
  return time.join("-") + " ";
};

export { FormatDate };
