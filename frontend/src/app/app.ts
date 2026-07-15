import { Component, inject, signal, effect } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { NgClass } from '@angular/common';
import { ToastService } from './shared/services/toast.service';
import { ConfirmService } from './shared/services/confirm.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, NgClass],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  readonly toastService = inject(ToastService);
  readonly confirmService = inject(ConfirmService);

  readonly isDarkTheme = signal(true);
  readonly sidebarOpen = signal(false);

  constructor() {
    // Effect to toggle the theme on the HTML element dynamically
    effect(() => {
      const root = document.documentElement;
      if (this.isDarkTheme()) {
        root.classList.remove('light-theme');
      } else {
        root.classList.add('light-theme');
      }
    });
  }

  toggleTheme(): void {
    this.isDarkTheme.update(dark => !dark);
    this.toastService.showInfo(`Switched to ${this.isDarkTheme() ? 'Dark' : 'Light'} theme`);
  }

  toggleSidebar(): void {
    this.sidebarOpen.update(open => !open);
  }

  closeSidebar(): void {
    this.sidebarOpen.set(false);
  }
}
