function errorCode() {
    Swal.fire({
        icon: 'error',
        background: '../static/Media/Home_background.jpg',
        confirmButtonColor: '#ffffff',
        confirmButtonText: '<a href="http://localhost:8080">Ok</a>',
        title: 'Oops...',
        text: 'Something went wrong!',
        footer: '<a href="http://localhost:8080">Why do I have this issue?</a>'
    })
}

function deleteBooking() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                'Deleted!',
                'Your booking has been deleted.',
                'success'
            )
        }
    })
}

function finalSuccess() {
    Swal.fire({
        title: 'Thank you for booking with us !!!',
        text: 'Confirmation email has been sent to your email',
        width: 600,
        padding: '3em',
        color: '#837eb9',
        confirmButtonText: '<a href="http://localhost:8080">Go To Home Page</a>',
        background: '#fff url(../Media/Home_background.jpg)',
        backdrop: `
    rgba(0,0,123,0.4)
    url("../Media/camera.gif")
    left top
    no-repeat
  `
    })
}

function forLogout() {
    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to log out?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#e3ab0a',
        confirmButtonText: '<a  href="http://localhost:8080/logout">Logout</a>'

    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                'Logged Out!',
                'You have successfully logged out.',
                'success'
            )
        }
    })
}