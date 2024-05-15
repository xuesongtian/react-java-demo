import { getLocalMenu, saveLocalMenu } from "../utils";
import { getMenu } from "@/api";
import { menuMock } from '../mock/menu';
import { MenuResponse } from "@/types"
let currentJob: Promise<MenuResponse> | null
export function getMenus() {
  if (currentJob) {
    return currentJob
  }
  const job: Promise<MenuResponse> = new Promise((reslove) => {
    let localMenu = getLocalMenu();
    if (localMenu) {
      return reslove(localMenu);
    }
    getMenu()
      .then((result) => {

        saveLocalMenu(menuMock);
        reslove(menuMock);
      })
      .catch((err) => {
        reslove([]);
      });
  });
  currentJob = job
  job.finally(() => { currentJob = null })
  return job
}
