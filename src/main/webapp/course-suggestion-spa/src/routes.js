import Login from './components/login/Login.vue';
import Home from './components/home/Home.vue';
import SuggestCourse from './components/suggest-course/SuggestCourse.vue';
import SuggestDiscipline from './components/suggest-discipline/SuggestDiscipline.vue';
import SuggestContent from './components/suggest-content/SuggestContent.vue';

export const routes = [
    { path: '', component: Login, title: 'Login', show: false },
    { path: '/home', component: Home, title: 'Página Inicial', show: false },
    { path: '/suggestcourse', component: SuggestCourse, title: 'Sugerir um curso', show: true },
    { path: '/suggestdiscipline', component: SuggestDiscipline, title: 'Sugerir uma disciplina', show: true },
    { path: '/suggestcontent', component: SuggestContent, title: 'Sugerir um conteúdo', show: true }
];