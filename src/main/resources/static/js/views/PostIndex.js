import createView from "../createView.js";

const BASE_URL = "http://localhost:8080/api/posts";

export default function PostIndex(props) {
    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => `<h3>${post.title}</h3>
                                            <p>${post.content}</p>`).join('')}
            </div>
            <div id="add-post-form">
                <div>
                    <input type="text" class="form-control" id="add-post-title" placeholder="Add Post Title">
                </div>
                <br>
                <div>
                    <textarea class="form-control" rows="4" id="add-post-content" placeholder="Add Post Content"></textarea>
                </div>
                <br>
                <div>
                    <button type="submit" class="btn btn-primary" id="submit-btn">Submit</button>
                </div>
            </div>
        </main>
    `;
}

export function PostsEvent() {
    createAddPostListener();
}

function createAddPostListener() {
    $(document).on('click', '#submit-btn',function (e) {
        e.preventDefault();
        console.log("clicked")
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }

        const request = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPost)
        }

        fetch(`${BASE_URL}`, request)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });

    })
}