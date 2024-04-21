import { getTag } from "./tag-component.js";

export const renderPost = (post) => {

    return getTag(post.tags[0]);

}