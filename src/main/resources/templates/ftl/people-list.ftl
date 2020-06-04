<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="img/favicon.ico">

  <title>People list</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
        integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
  <!-- Bootstrap core CSS -->
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-8 offset-2">
      <div class="panel panel-default user_panel">
        <div class="panel-heading">
          <h3 class="panel-title">User List</h3>
        </div>
        <div class="panel-body">
          <div class="table-container">
            <table class="table-users table" border="0">
              <tbody>
              <tr>
                  <#list listOfLikedUsers as users>
                <td width="10">
                  <div class="avatar-img">
                    <img class="img-circle" style="width:100px;height:100px;" src="${users.photo_url}"/>
                  </div>
                </td>
                <td class="align-middle">
                  <!--  Herbert Hoover               -->
                    ${users.id}
                </td>
                <td class="align-middle">
                  <!--Builder Sales Agent-->
                    ${users.profession}
                </td>
                <td class="align-middle">
                  You liked this user
                </td>
                <td class="align-middle">
                  <!--     Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small> -->
                  <form method="post">
                    <button type="submit" name="action" value="sendMessage">Send message</button>
                  </form>
                </td>
              </tr>
              </#list>
              </tbody>
            </table>
            <form method="post">
              <button type="submit" name="action" value="logout" style="width: 20%; height: 20%;">Logout</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>