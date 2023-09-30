<template>
    <div>
        <div class="tabs">
            <TabNav :tabs="['Home', 'All Students', 'Add Student', 'Files']" :selected="selected" @selected="setSelected">
                <Tab :isSelected="selected === 'Home'">
                    <CourseHomePage />
                </Tab>
                <Tab :isSelected="selected === 'All Students'">
                    <CourseStudentListPage />
                </Tab>
                <Tab :isSelected="selected === 'Add Student'">
                    <CourseAddStudentPage />
                </Tab>
                <Tab :isSelected="selected === 'Files'">
                    <FilePage />
                </Tab>
            </TabNav>
        </div>
    </div>
</template>

<script>
import TabNav from '../components/TabNav.vue'
import Tab from '../components/Tab.vue'
import CourseHomePage from '../components/CourseHomePage.vue'
import CourseStudentListPage from '../components/CourseStudentListPage.vue'
import CourseAddStudentPage from '../components/CourseAddStudentPage.vue'
import FilePage from '../components/FilePage.vue'

export default {
  name: 'coursePage',
  props: ['coursePage'],
  components: { TabNav, Tab, CourseHomePage, CourseStudentListPage, CourseAddStudentPage, FilePage },
  data() {
    return {
      selected: 'Home'
    }
  },
  methods: {
    setSelected(Tab) {
      this.selected = Tab
    }
  }
}
</script>

<style scoped>
.tabs {
    display: flex;
    flex-direction: row;
    justify-content: center;
    cursor: pointer;
}
</style>
