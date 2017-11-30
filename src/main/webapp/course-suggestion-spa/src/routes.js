import Home from './components/home/Home.vue';
import SelectCourse from './components/select-course/SelectCourse.vue';

export const routes = [
    { path: '', component: Home, titulo: 'Home' },
    { path: '/selectCourse', component: SelectCourse, titulo: 'Selecione o curso' }
];